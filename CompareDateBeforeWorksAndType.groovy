import com.atlassian.jira.component.ComponentAccessor
import com.atlassian.jira.issue.IssueManager
import com.opensymphony.workflow.InvalidInputException

import java.sql.Timestamp

IssueManager issueManager = ComponentAccessor.getIssueManager()
def customFieldManager = ComponentAccessor.getCustomFieldManager()
Calendar rightNow = Calendar.getInstance()

Timestamp start =  issue.getCustomFieldValue(customFieldManager.getCustomFieldObject(12017))
def now = new Timestamp(rightNow.getTimeInMillis())
def diff = (int)(start.getTime() - now.getTime())/1000
String worksType = issue.getCustomFieldValue(customFieldManager.getCustomFieldObject(12026)).toString()

if ((diff > (24 * 3600)) && (worksType != "��������")){
    invalidInputException = new InvalidInputException("���� ������ ����������� ����� ��� �� 24 ����, ���������� ��������� - ��������")
}else if ((diff < (24 * 3600) && (diff > (4 * 3600))) && worksType != "�����������"){
    invalidInputException = new InvalidInputException("���� ������ ����������� ����� ��� �� 24 ���� � ����� ��� 4 ����, ���������� ��������� - �����������")
}else if ((diff < (4 * 3600)) && (worksType != "���������")){
    invalidInputException = new InvalidInputException("���� ������ ����������� ����� ��� �� 4 ����, ���������� ��������� - ���������")
}