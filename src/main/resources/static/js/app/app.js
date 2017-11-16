
var app = angular.module('crudApp',['ui.router','ngStorage', 'ui.bootstrap.datetimepicker', 'naif.base64']);
 
app.constant('urls', (function() {
//	URL_BASE = 'http://127.0.0.1:7777'
	URL_BASE = 'http://181.118.174.70:8080/restExternal-0.0.1-SNAPSHOT';
	
	return {
		DIRECTORY_BASE: '/restExternal-0.0.1-SNAPSHOT/',
//		DIRECTORY_BASE: '/',
		BASE: URL_BASE + '/patient/',
		GETALL: URL_BASE + '/patient/patients',
	    CREATE: URL_BASE + '/patient/create',
	    UPDATE: URL_BASE + '/patient/update/',
	    DELETE: URL_BASE + '/patient/delete/',
	    
	    BASE_DOCUMENT: URL_BASE + '/document/',
	    GETALL_DOCUMENTS: URL_BASE + '/document/documents/',
	    CREATE_DOCUMENT: URL_BASE + '/document/create/',
	    UPDATE_DOCUMENT: URL_BASE + '/document/update/',
	    DELETE_DOCUMENT: URL_BASE + '/document/delete/'
	}
	
    
})());
 
app.config(['$stateProvider', '$urlRouterProvider', 'urls',
    function($stateProvider, $urlRouterProvider, urls) {
	
		moment.locale('es');
	
        $stateProvider
            .state('patient', {
                url: '/patient',
                templateUrl: urls.DIRECTORY_BASE + 'patient.html',
                controller:'PatientController',
                controllerAs:'ctrl',
                resolve: {
                	patients: function ($q, PatientService) {
                        console.log('Cargando los Pacientes');
                        var deferred = $q.defer();
                        PatientService.loadAllPatients().then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            }).state('document', {
                url: '/document/:patientId',
                templateUrl: urls.DIRECTORY_BASE + 'document.html',
                controller:'DocumentController',
                controllerAs:'ctrl',
                resolve: {
                	documents: function ($q, $stateParams, DocumentService) {
                        console.log('Cargando los documentos');
                        var deferred = $q.defer();
                        DocumentService.loadAllDocuments($stateParams.patientId).then(deferred.resolve, deferred.resolve);
                        return deferred.promise;
                    }
                }
            });
        $urlRouterProvider.otherwise('/patient');
    }]);