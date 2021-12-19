<%-- 
    Document   : form
    Created on : May 17, 2017, 9:30:19 PM
    Author     : MotYim <mohamed.motyim@gmail.com>
--%>
<div class="box box-primary">
    <div class="box-header with-border">
        <h3 class="box-title">Form Header</h3>
    </div>
    <!-- /.box-header -->
    <!-- form start -->
    <form class="form-horizontal">
        <div class="box-body">

            <!-- text feild -->
            <div class="form-group">
                <label for="inputEmail3" class="col-sm-2 control-label">Email</label>

                <div class="col-sm-10">
                    <input type="email" class="form-control" id="inputEmail3" placeholder="Email"/>
                </div>
            </div>

            <!-- password -->
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">Password</label>

                <div class="col-sm-10">
                    <input type="password" class="form-control" id="inputPassword3" placeholder="Password"/>
                </div>
            </div>

            <!-- input file -->
            <div class="form-group">
                <label for="exampleInputFile" class="col-sm-2 control-label">File input</label>
                <div class="col-sm-10">
                    <input type="file" id="exampleInputFile">
                </div>
            </div>

            <!-- text area -->
            <div class="form-group">
                <label  class="col-sm-2 control-label">Textarea</label>
                <div class="col-sm-10">
                    <textarea class="form-control" rows="3" placeholder="Enter ..."></textarea>
                    <p class="help-block">this is the help text.</p>
                </div>
            </div>

            <!-- check -->
            <div class="form-group">
                <label  class="col-sm-2 control-label">CheckBox</label>
                <div class="col-sm-10">
                    <div class="checkbox">
                        <label>

                            <input type="checkbox">
                            Checkbox 1
                        </label>
                    </div>

                    <div class="checkbox">
                        <label>
                            <input type="checkbox">
                            Checkbox 2
                        </label>
                    </div>
                </div>
            </div>

            <!-- radio -->
            <div class="form-group">
                <label class="col-sm-2 control-label">RadioButton</label>
                <div class="col-sm-10">
                    <div class="radio">
                        <label>
                            <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
                            Option one is this and that&mdash;be sure to include why it's great
                        </label>
                    </div>
                    <div class="radio">
                        <label>
                            <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                            Option two can be something else and selecting it will deselect option one
                        </label>
                    </div>
                </div>
            </div>

            <!-- select -->
            <div class="form-group">
                <label class="col-sm-2 control-label">Select</label>
                <div class="col-sm-10">
                    <select class="form-control">
                        <option>option 1</option>
                        <option>option 2</option>
                        <option>option 3</option>
                        <option>option 4</option>
                        <option>option 5</option>
                    </select>
                </div>
            </div>

            <!-- Select multiple-->
            <div class="form-group">
                <label class="col-sm-2 control-label">Select Multiple</label>
                <div class="col-sm-10">
                    <select multiple class="form-control">
                        <option>option 1</option>
                        <option>option 2</option>
                        <option>option 3</option>
                        <option>option 4</option>
                        <option>option 5</option>
                    </select>
                </div>
            </div>
            <!-- Color Picker -->
            <div class="form-group">
                <label class="col-sm-2 control-label">Color picker with addon:</label>
                <div class="col-sm-10">
                    <div class="input-group my-colorpicker2">
                        <input type="text" class="form-control">

                        <div class="input-group-addon">
                            <i></i>
                        </div>
                    </div>
                </div>
            </div>

            <!-- time Picker -->
            <div class="bootstrap-timepicker">
                <div class="form-group">
                    <label class="col-sm-2 control-label">Time picker:</label>
                    <div class="col-sm-10">
                        <div class="input-group">
                            <input type="text" class="form-control timepicker">

                            <div class="input-group-addon">
                                <i class="fa fa-clock-o"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Date -->
            <div class="form-group">
                <label class="col-sm-2 control-label">Date:</label>
                <div class="col-sm-10">
                    <div class="input-group date">
                        <div class="input-group-addon">
                            <i class="fa fa-calendar"></i>
                        </div>
                        <input type="text" class="form-control pull-right" id="datepicker">
                    </div>
                </div>
            </div>

        </div>
        <!-- /.box-body -->

        <div class="box-footer">
            <button type="submit" class="btn btn-default">Cancel</button>
            <button type="submit" class="btn btn-primary pull-right">Submit</button>
        </div>
    </form>
</div>