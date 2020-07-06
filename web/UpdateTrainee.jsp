<%-- 
    Document   : UpdateTrainee
    Created on : Jun 8, 2020, 11:48:29 AM
    Author     : Ngoc Do Minh
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <style>
        .table-content {
            width: 900px;        
            margin: 50px auto;
            border: 1px solid black;
        }
        td {
            padding-left: -100px;
            width: 100px;
        }
        td select{
            width: 180px;
        }
        input {
            font-family: "Roboto", sans-serif;
            width: auto;
            height: 50%;
            border: 1;
            box-sizing: border-box;
            font-size: 14px;
        }
        .table-content .tbl
        {
            margin-left: 50px;
            width: 800px;
        }
    </style>
    <script type="text/javascript">
        function confirm_Delete()
        {
            return confirm("Do you want to delete? This action will not be recoverable");
        }
        ;
    </script>
    <jsp:include page="header.jsp"/>
    <jsp:include page="navigationbar.jsp"/>
    <div class="table-content">
        <input type="button" value="Back to main page" style="margin-left: 40px; margin-top: 20px; margin-bottom: -20px; font-size: 10px;">
        <form action="updateTrainee.action" method="POST">
            <table class="table table-responsive tbl">
                <thead class="table-responsive table-dark">
                    <tr>
                        <th colspan="4"><h4 style="text-align: center;">Update trainee</h4></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Trainee ID:</td>
                        <td><input type="text" name="traineeID" value="<s:property value="trainee.traineeID"/>" readonly/></td>
                        <td>Trainee Name:</td>
                        <td><input type="text" name="traineeName" value="<s:property value="trainee.traineeName"/>"/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="text" name="traineePassword" value="<s:property value="trainee.getPassword()"/>"/></td>
                        <td>Date of birth:</td>
                        <td><input type="date" name="traineeDoB" style="width: 188px;" value="<s:property value="trainee.traineeDoB"/>"/></td>
                    </tr>
                    <tr>
                        <td>Trainee Phonenumber:</td>
                        <td><input type="text" name="traineePhoneNumber" value="<s:property value="trainee.traineePhoneNumber"/>"/></td>
                        <td>Trainee Email:</td>
                        <td><input type="text" name="traineeEmail" value="<s:property value="trainee.traineeEmail"/>"/></td>
                    </tr>
                    <tr>
                        <td>Trainee Address:</td>
                        <td colspan="3"><textarea name="traineeAddress" cols="86" rows="4"><s:property value="trainee.traineeAddress"/></textarea></td>
                    </tr>
                    <tr>
                        <td>Trainee Detail:</td>
                        <td colspan="3"><textarea name="traineeDetail" cols="86" rows="4"><s:property value="trainee.traineeDetail"/></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align: center;">
                            <input type="submit" value="Update" class="btn btn-success">
                            <a class="btn btn-danger" href="deleteTrainee.action?traineeID=<s:property value="trainee.traineeID"/>" onclick="return confirm_Delete();">Delete</a>
                            <a href="viewTrainee.action" class="btn btn-primary">Back</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>
    <jsp:include page="footer.jsp"/>
</html>