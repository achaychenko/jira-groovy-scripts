import com.atlassian.jira.component.ComponentAccessor
import com.opensymphony.workflow.InvalidInputException

def customFieldManager = ComponentAccessor.getCustomFieldManager()
def CfValue = issue.getCustomFieldValue(customFieldManager.getCustomFieldObject(11328)).toString()

def componentName = issue.getIssueTypeObject().name
def projectKey = issue.getProject().get("key")

if (projectKey == "TN" && componentName == "����������� ���������� ���������" && CfValue == "30 ����"){
    invalidInputException = new InvalidInputException("����� 30 ���� ����������!")
}