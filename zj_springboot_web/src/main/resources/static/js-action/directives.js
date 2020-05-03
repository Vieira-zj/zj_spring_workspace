// 自定义指令
actionApp.directive('datePicker', function() {
	return {
		restrict : 'AC',
		link : function(scope, elem, attrs) {
			// 初始化jqueryui的datePicker（jquery的写法是 $('#id').datepicker()）
			elem.datepicker();
		}
	};
});