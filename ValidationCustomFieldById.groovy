import com.atlassian.jira.component.ComponentAccessor
import com.opensymphony.workflow.InvalidInputException

def customFieldManager = ComponentAccessor.getCustomFieldManager()
def eMailCf = customFieldManager.getCustomFieldObject(10200)

if (issue.getCustomFieldValue(eMailCf) == null){
    invalidInputException = new InvalidInputException("Заполните поле Дата начала работы")
}

