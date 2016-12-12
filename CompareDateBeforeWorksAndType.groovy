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

if ((diff > (24 * 3600)) && (worksType != "Плановые")){
    invalidInputException = new InvalidInputException("Если работы планируются более чем за 24 часа, необходимо выставить - Плановые")
}else if ((diff < (24 * 3600) && (diff > (4 * 3600))) && worksType != "Внеплановые"){
    invalidInputException = new InvalidInputException("Если работы планируются менее чем за 24 часа и более чем 4 часа, необходимо выставить - Внеплановые")
}else if ((diff < (4 * 3600)) && (worksType != "Аварийные")){
    invalidInputException = new InvalidInputException("Если работы планируются менее чем за 4 часа, необходимо выставить - Аварийные")
}