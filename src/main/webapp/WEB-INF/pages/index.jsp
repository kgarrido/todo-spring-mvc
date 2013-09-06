<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<title>Todo Notifications App</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href='<c:url value="resources/css/bootstrap.min.css" />' media="screen">
    <link rel="stylesheet" href='<c:url value="resources/css/bootstrap-theme.min.css" />' media="screen">


</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="jumbotron">
                <h1>Todo Notifications App</h1>
                <p>Let send notifications thanks to probes!</p>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <table class="table table-striped table-bordered">
                <thead>
                    <tr>
                        <th>Task</th>
                        <th>Done ?</th>
                        <th>Delete ?</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${model.tasks}" var="task">
                        <tr>
                            <td><c:out value="${task.name}" /></td>
                            <td><a class="btn btn-primary" href="task-done-${task.id}">Mark as done</a></td>
                            <td>

                                <a class="btn btn-danger" href="task-delete-${task.id}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>

                    <c:forEach items="${model.completedTasks}" var="task">
                        <tr>
                            <td><c:out value="${task.name}" /></td>
                            <td><button class="btn btn-success disabled">Good job!</button></td>
                            <td>
                                <a class="btn btn-danger" href="completed-task-delete-${task.id}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <form:form  modelAttribute="task" method="POST" action="" cssClass="form-inline">
                <div class="form-group">
                    <label class="control-label sr-only" for="name">Task</label>
                    <form:input path="name" id="name" cssClass="form-control" placeholder="Task name"/>
                </div>
                <button type="submit" class="btn btn-primary">Add task</button>
            </form:form>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-6">
            <p>
                <a class="btn btn-primary" href="export-list" onClick="alertExport();">Export list of tasks</a>
            </p>
        </div>
        <div class="col-xs-6">
            <p>
                <a class="btn btn-primary" href="do-push" onClick="alertPush();">Do a push</a>
            </p>
        </div>
    </div>
</div>

<script>
    function alertOK() {
        alert("The list of tasks has been created in /Users/Matthis/Desktop/tasks.txt");
    }

    function alertOK() {
        alert("A push notification has been sent");
    }
</script>


</body>
</html>