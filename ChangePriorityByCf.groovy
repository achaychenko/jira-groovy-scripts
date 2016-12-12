import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.event.type.EventDispatchOption;

IssueManager issueManager = ComponentAccessor.getIssueManager();

//MutableIssue issue = issueManager.getIssueObject("AC-96");

MutableIssue CurIssue = (MutableIssue) issue;
def Priority = "5";
def customFieldManager = ComponentAccessor.getCustomFieldManager();
def Criticality = customFieldManager.getCustomFieldObject(12011);

switch (CurIssue.getCustomFieldValue(Criticality)){
    case "������� (�� 10 �.�.)":
        Priority = "5";
        break;
    case "������� (�� 5 �.�.)":
        Priority = "4";
        break;
    case "���������� (3 �.�.)":
        Priority = "3";
        break;
    case "������� (2 �.�.)":
        Priority = "2";
        break;
    case "����������� (�� 1 �.�.)":
        Priority = "1";
        break;
}

CurIssue.setPriorityId(Priority);

ComponentAccessor.getIssueManager().updateIssue(ComponentAccessor.getUserUtil().getUserObject('automation'), CurIssue, EventDispatchOption.DO_NOT_DISPATCH, false);
