import com.atlassian.crowd.embedded.api.User
import com.atlassian.jira.bc.issue.search.SearchService
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.Issue
import com.atlassian.jira.issue.IssueManager
import com.atlassian.jira.user.util.UserUtil
import com.atlassian.jira.web.bean.PagerFilter
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.vote.VoteManager
import com.atlassian.jira.issue.IssueInputParameters
import org.apache.log4j.Logger
import org.apache.log4j.Level

def log = Logger.getLogger("PW service")
log.setLevel(Level.DEBUG)
log.debug "PW script runned"

jqlSearch = "project = PW AND status = \"На согласовании\""
SearchService searchService = ComponentAccessor.getComponent(SearchService.class)
UserUtil userUtil = ComponentAccessor.getUserUtil()
User user = userUtil.getUserObject('SMS-Sender')
IssueManager issueManager = ComponentAccessor.getIssueManager()
VoteManager voteManager = ComponentAccessor.getVoteManager()
def issueService = ComponentAccessor.getIssueService()

def AuthContext=ComponentAccessor.getJiraAuthenticationContext()
AuthContext.setLoggedInUser(user)

List<Issue> issues = null

SearchService.ParseResult parseResult =  searchService.parseQuery(user, jqlSearch)
if (parseResult.isValid()) {
    def searchResult = searchService.search(user, parseResult.getQuery(), PagerFilter.getUnlimitedFilter())
    // Transform issues from DocumentIssueImpl to the "pure" form IssueImpl (some methods don't work with DocumentIssueImps)
    issues = searchResult.issues.collect {issueManager.getIssueObject(it.id)}
} else {
    log.error("Invalid JQL: " + jqlSearch);
}

def customFieldManager = ComponentAccessor.getCustomFieldManager()
def Cf = customFieldManager.getCustomFieldObject(12015)

def sb = ''<<''

issues.each {
    def voters = voteManager.getVoterUserkeys(it).sort()
    if(voters.equals(it.getCustomFieldValue(Cf).key.sort())){

        IssueInputParameters issueInputParameters = issueService.newIssueInputParameters()

        issueInputParameters.with {
            setComment("auto agreement")
        }

        def validationResult = issueService.validateTransition(user, it.id, 11, issueInputParameters)

        def issueResult = issueService.transition(user, validationResult)

    }

}


