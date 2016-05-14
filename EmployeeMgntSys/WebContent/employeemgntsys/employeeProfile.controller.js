sap.ui.controller("ems.employeemgntsys.employeeProfile", {

	/**
	 * Called when a controller is instantiated and its View controls (if
	 * available) are already created. Can be used to modify the View before it
	 * is displayed, to bind event handlers and do other one-time
	 * initialization.
	 * 
	 * @memberOf employeemgntsys.employeeProfile
	 */
	onInit : function() {
		var url = location.href;
		var id = url.substr(url.lastIndexOf("/") + 1);
		var oModel = new sap.ui.model.json.JSONModel(
				"/EmployeeMgntSys/api/employee/" + id);
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
	 * @memberOf employeemgntsys.employeeProfile
	 */
	// onBeforeRendering: function() {
	//
	// },
	/**
	 * Called when the View has been rendered (so its HTML is part of the
	 * document). Post-rendering manipulations of the HTML could be done here.
	 * This hook is the same one that SAPUI5 controls get after being rendered.
	 * 
	 * @memberOf employeemgntsys.employeeProfile
	 */
	// onAfterRendering: function() {
	//
	// },
	/**
	 * Called when the Controller is destroyed. Use this one to free resources
	 * and finalize activities.
	 * 
	 * @memberOf employeemgntsys.employeeProfile
	 */
	// onExit: function() {
	//
	// }
	onPressEdit : function(oEvent) {
		var oData = this.getView().getModel().getData();
		var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
		oRouter.navTo("edit", {
			id : oData["id"]
		});
	},

	onPressBack : function() {
		history.back();
	}

});