sap.ui.controller("ems.employeemgntsys.employeeAdd", {

	/**
	 * Called when a controller is instantiated and its View controls (if
	 * available) are already created. Can be used to modify the View before it
	 * is displayed, to bind event handlers and do other one-time
	 * initialization.
	 * 
	 * @memberOf employeemgntsys.employeeAdd
	 */
	onInit : function() {
		var oModel = new sap.ui.model.json.JSONModel();
		this.getView().setModel(oModel);
	},
	// _onObjectMatched : function(oEvent) {
	// this.getView().bindElement({
	// path : "/" + oEvent.getParameter("arguments").id,
	// model : "id"
	// });
	// },
	/**
	 * Similar to onAfterRendering, but this hook is invoked before the
	 * controller's View is re-rendered (NOT before the first rendering!
	 * onInit() is used for that one!).
	 * 
	 * @memberOf employeemgntsys.employeeAdd
	 */
	// onBeforeRendering: function() {
	//
	// },
	/**
	 * Called when the View has been rendered (so its HTML is part of the
	 * document). Post-rendering manipulations of the HTML could be done here.
	 * This hook is the same one that SAPUI5 controls get after being rendered.
	 * 
	 * @memberOf employeemgntsys.employeeAdd
	 */
	// onAfterRendering: function() {
	//
	// },
	/**
	 * Called when the Controller is destroyed. Use this one to free resources
	 * and finalize activities.
	 * 
	 * @memberOf employeemgntsys.employeeAdd
	 */
	// onExit: function() {
	//
	// }
	onPressSave : function() {
		var oFileUploader = this.getView().byId("fileUploader");
		oFileUploader.upload();
	},

	onUploadCompleted : function(oEvent) {
		var id = $.cookie("imageId");
		// 2016/2/15 Chrome已得到imageId，但是id中无法读取到，原因是cookie和页面的path不同(Solved)
		console.log(id);
		var data = this.getView().getModel().getData();
		data["photoId"] = id;
		console.log(data);
		$.ajax({
			url : '/EmployeeMgntSys/api/employee',
			contentType : 'application/json',
			dataType : "json",
			data : JSON.stringify(data),
			type : 'POST',
		// success : function(oData) {
		// alert(oData);
		// },
		});
	},

	onPressCancel : function(oEvent) {
		history.back();
	},
});