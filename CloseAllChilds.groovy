import com.atlassian.jira.ComponentManager
import com.atlassian.jira.issue.comments.CommentManager
import com.opensymphony.workflow.WorkflowContext
import org.apache.log4j.Category
import com.atlassian.jira.config.SubTaskManager
import com.atlassian.jira.workflow.WorkflowTransitionUtil;
import com.atlassian.jira.workflow.WorkflowTransitionUtilImpl;
import com.atlassian.jira.util.JiraUtils;

log = Category.getInstance("com.onresolve.jira.groovy.AutoCloseChildIssues")

String currentUser = ((WorkflowContext) transientVars.get("context")).getCaller();
WorkflowTransitionUtil workflowTransitionUtil = ( WorkflowTransitionUtil ) JiraUtils.loadComponent( WorkflowTransitionUtilImpl.class );

SubTaskManager subTaskManager = ComponentManager.getInstance().getSubTaskManager();
Collection subTasks = issue.getSubTaskObjects()
if (subTaskManager.subTasksEnabled && !subTasks.empty) {
    subTasks.each {
        log.debug("issue.statusObject.name: " + issue.statusObject.name)
        workflowTransitionUtil.setIssue(it);
        workflowTransitionUtil.setUsername(currentUser);

        if (it.getIssueTypeObject().getName() == "Sub-task") {
            workflowTransitionUtil.setAction(211)
        }else if (it.getIssueTypeObject().getName() == "TestTask"){
            workflowTransitionUtil.setAction(151)
        }else if (it.getIssueTypeObject().getName() == "ServiceTask"){
            workflowTransitionUtil.setAction(191)
        }else if (it.getIssueTypeObject().getName() == "DevTask"){
            workflowTransitionUtil.setAction(121)
        }else if (it.getIssueTypeObject().getName() == "VASTask"){
            workflowTransitionUtil.setAction(111)
        }
        it.setResolutionId()
        // Add a comment so people have a clue why the child has been closed
        CommentManager commentManager = (CommentManager) ComponentManager.getComponentInstanceOfType(CommentManager.class);
        String comment = "Автоматическое закрытие подзадач";
        commentManager.create(it, currentUser, comment, true);

        // validate and transition issue
        workflowTransitionUtil.validate();
        workflowTransitionUtil.progress();

    }
}