
var app = angular.module('crudApp',['ui.router','ngStorage', 'naif.base64']);
 
app.constant('urls', {
	BASE: 'http://7.214.104.187:7777/patient/',
	GETALL: 'http://7.214.104.187:7777/patient/patients',
    CREATE: 'http://7.214.104.187:7777/patient/create',
    UPDATE: 'http://7.214.104.187:7777/patient/update/',
    DELETE: 'http://7.214.104.187:7777/patient/delete/',
    
    BASE_DOCUMENT: 'http://7.214.104.187:7777/document/',
    GETALL_DOCUMENTS: 'http://7.214.104.187:7777/document/documents/',
    CREATE_DOCUMENT: 'http://7.214.104.187:7777/document/create/',
    UPDATE_DOCUMENT: 'http://7.214.104.187:7777/document/update/',
    DELETE_DOCUMENT: 'http://7.214.104.187:7777/document/delete/'
    
});
 
app.config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
 
        $stateProvider
            .state('patient', {
                url: '/patient',
                templateUrl: '/patient.html',
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
                templateUrl: '/document.html',
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