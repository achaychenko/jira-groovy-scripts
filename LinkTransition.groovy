import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.CustomFieldManager
import com.atlassian.jira.issue.fields.CustomField
import com.atlassian.jira.issue.link.IssueLinkManager
import com.opensymphony.workflow.InvalidInputException

CustomFieldManager customFieldManager = ComponentAccessor.getCustomFieldManager()
CustomField resultRasm = customFieldManager.getCustomFieldObject(11329)

IssueLinkManager issueLinkManager =  ComponentAccessor.getIssueLinkManager()

def value = issue.getCustomFieldValue(resultRasm).get("1").toString()

request = webwork.action.ActionContext.getRequest()
def issueLinkManager = ComponentAccessor.getIssueLinkManager()

oldLink = issueLinkManager.getInwardLinks(issue.getId())

if (oldLink.isEmpty() && request){
    linkedIssue = request.getParameter('issuelinks-issues')
    if(!linkedIssue){
        if(	value == "������ ��������������" ||
                value == "���� ����������� ���������, ���� �������� ������, ������� �� ������ ������ ���������" ||
                value == "� ����������, �������� � ��������������� �����" ||
                value == "������� � ������ ���������������" ||
                value == "������� ��������������" ||
                value == "������� ��������������, ������� ��������� �������" ||
                value == "� ��������� ������ �� �������� SMS, ���������� ��������� ��������� �� �������� (����� SMSC), ���� �������� SIM �����" ||
                value == "�� ����� ���������� ���� ����������� ���������, ���� �������� ������, ������ ������� � ���������, � ���� �������� ��� ��������� �������"
        ){
            invalidInputException = new InvalidInputException("����� ��������� ������!!")
        }
    }
}
