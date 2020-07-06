<%-- 
    Document   : AddNewTrainee
    Created on : May 20, 2020, 2:30:00 PM
    Author     : Ngoc Do Minh
--%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
    $(function () {
        $.datepicker.setDefaults({
            onClose: function (date, inst) {
                $("#traineeDoB").html(date);
            }
        });

        $("#traineeDoB").datepicker();
    });
</script>
<html>
    <jsp:include page="header.jsp"/>
    <jsp:include page="navigationbar.jsp"/>
    <div class="table-content">
        <input type="button" value="Back to main page" style="margin-left: 40px; margin-top: 20px; margin-bottom: -20px; font-size: 10px;">
        <form action="addNewTrainee.action" method="POST">
            <table class="table table-responsive tbl">
                <thead class="table-responsive table-dark">
                    <tr>
                        <th colspan="4"><h4 style="text-align: center;">Add new trainee</h4></th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Trainee ID:</td>
                        <td><input type="text" name="traineeID" placeholder="Enter traineeID"/></td>
                        <td>Trainee Name:</td>
                        <td><input type="text" name="traineeName" placeholder="Enter trainee name"/></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="text" name="traineePassword" placeholder="Enter trainee password"/></td>
                        <td>Date of birth:</td>
                        <td><input type="date" name="traineeDoB" style="width: 188px;"/></td>
                    </tr>
                    <tr>
                        <td>Trainee Phonenumber:</td>
                        <td><input type="text" name="traineePhoneNumber" placeholder="Enter phone number"/></td>
                        <td>Trainee Email:</td>
                        <td><input type="text" name="traineeEmail" placeholder="Enter trainee email"/></td>
                    </tr>
                    <tr>
                        <td>Trainee Address:</td>
                        <td colspan="3"><textarea name="traineeAddress" cols="86" rows="4" placeholder="Enter trainee Address"></textarea></td>
                    </tr>
                    <tr>
                        <td>Trainee Detail:</td>
                        <td colspan="3"><textarea name="traineeDetail" cols="86" rows="4" placeholder="Enter trainee detail (E.g: TOEIC score, Main programming language)"></textarea></td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align: center;">
                            <input type="submit" value="Add" class="btn btn-success">
                            <input type="reset" value="Reset" class="btn btn-info"/>
                            <a href="viewTrainee.action" class="btn btn-primary">Back</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>
    <jsp:include page="footer.jsp"/>
</html>
