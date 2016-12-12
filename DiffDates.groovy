import com.atlassian.jira.component.ComponentAccessor
import java.sql.Timestamp

def customFieldManager = ComponentAccessor.getCustomFieldManager()
Timestamp start =  issue.getCustomFieldValue(customFieldManager.getCustomFieldObject(10013))
Timestamp end   =  issue.getCustomFieldValue(customFieldManager.getCustomFieldObject(12103))
String diff = (end.getTime() - start.getTime())/60000

return diff + " минут"

