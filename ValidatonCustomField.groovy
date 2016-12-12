import com.atlassian.jira.component.ComponentAccessor
import com.opensymphony.workflow.InvalidInputException

def customFieldManager = ComponentAccessor.getCustomFieldManager()
def eMailCf = customFieldManager.getCustomFieldObjectByName("E-mail")



if (issue.getCustomFieldValue(eMailCf) == null){
    invalidInputException = new InvalidInputException("Email field is empty")
}

