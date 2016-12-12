import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.IssueImpl
import com.atlassian.jira.issue.IssueManager
import com.opensymphony.workflow.InvalidInputException

IssueManager issueManager = ComponentAccessor.getIssueManager()

IssueImpl issue = issueManager.getIssueObject("AC-83")

return issue.getProject().

def componentName= issue.getComponentObjects()

if (!(componentName.size() == 1 & (componentName.name.contains("ДОК") | componentName.name.contains("КД")))){
    invalidInputException = new InvalidInputException("Поле компонент должнен быть ДОК или КД")
}




//return componentName.name.contains("BILLING")


