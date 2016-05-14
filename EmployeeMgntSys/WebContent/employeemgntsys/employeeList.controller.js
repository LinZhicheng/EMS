sap.ui.controller("ems.employeemgntsys.employeeList", {

	/**
	 * Called when a controller is instantiated and its View controls (if
	 * available) are already created. Can be used to modify the View before it
	 * is displayed, to bind event handlers and do other one-time
	 * initialization.
	 * 
	 * @memberOf employeemgntsys.employeeList
	 */
	onInit : function() {
		var oModel = new sap.ui.model.json.JSONModel(
				"/EmployeeMgntSys/api/employee");
		this.getView().setModel(oModel);
	},
	/**
	 * Similar to onAfterRendering, but this hook is invoked before the
	 * controller's View is re-rendered (NOT before the first rendering!
	 * onInit() is used for that one!).
	 * 
	 * @memberOf employeemgntsys.employeeList
	 */
	// onBeforeRendering: function() {
	//
	// },
	/**
	 * Called when the View has been rendered (so its HTML is part of the
	 * document). Post-rendering manipulations of the HTML could be done here.
	 * This hook is the same one that SAPUI5 controls get after being rendered.
	 * 
	 * @memberOf employeemgntsys.employeeList
	 */
	// onAfterRendering: function() {
	//
	// },
	/**
	 * Called when the Controller is destroyed. Use this one to free resources
	 * and finalize activities.
	 * 
	 * @memberOf employeemgntsys.employeeList
	 */
	// onExit: function() {
	//
	// }
	onPressAdd : function(oEvent) {
		var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
		oRouter.navTo("add");
	},

	onPressProfile : function(oEvent) {
		var oItem = oEvent.getSource();
		var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
		oRouter.navTo("profile", {
			id : oItem.getBindingContext().getProperty("id")
		});
	}
});