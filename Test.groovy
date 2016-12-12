import com.atlassian.jira.user.DelegatingApplicationUser
import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.IssueManager
import com.atlassian.jira.issue.MutableIssue

IssueManager issueManager = ComponentAccessor.getIssueManager();
MutableIssue issue = issueManager.getIssueObject("PW-36");
CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager();

Collection<DelegatingApplicationUser> users =  (Collection) issue.getCustomFieldValue(customFieldManager.getCustomFieldObject(12025));
List<String> usersName = new ArrayList<>();

ComponentAccessor.getCustomFieldManager().getCustomFieldObject(12026)

users.each{
    usersName.add(it.getDisplayName());
}
return usersName


String fw = issue.getCustomFieldValue(componentManager.getCustomFieldManager().getCustomFieldObjectByName("Дата и время начала"));

fw.toString()
