import com.atlassian.jira.component.ComponentAccessor
import com.opensymphony.workflow.InvalidInputException

def customFieldManager = ComponentAccessor.getCustomFieldManager()
def Cf = customFieldManager.getCustomFieldObject(12303)

def issueManager = ComponentAccessor.getIssueManager()
def originalIssue = issueManager.getIssueObject(issue.id)

if (issue.getCustomFieldValue(Cf).equals(originalIssue.getCustomFieldValue(Cf))){

    invalidInputException = new InvalidInputException("Введи фактическое время простоя сервисов!")
}
