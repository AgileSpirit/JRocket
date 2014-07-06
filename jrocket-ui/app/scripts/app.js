'use strict';

/**
 * @ngdoc overview
 * @name jrocketUiApp
 * @description
 * # jrocketUiApp
 *
 * Main module of the application.
 */
angular
    .module('jrocketUiApp', [
        'ngAnimate',
        'ngCookies',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch',
        'ui.bootstrap'
    ])
    .config(function ($routeProvider, $httpProvider, $locationProvider) {
        $routeProvider
            .when('/login', { templateUrl: 'views/login.html', controller: 'LoginCtrl' })
            .when('/about', { templateUrl: 'views/about.html', controller: 'AboutCtrl' })
            .when('/main', { templateUrl: 'views/main.html', controller: 'MainCtrl' })
            .when('/bookmarks', { templateUrl: 'views/bookmarks.html', controller: 'BookmarksCtrl' })
            .when('/error', { templateUrl: 'views/error.html', controller: 'ErrorCtrl' })
            .otherwise({ redirectTo: '/bookmarks' });

        /* CORS... */
        /* http://stackoverflow.com/questions/17289195/angularjs-post-data-to-external-rest-api */
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];

        /* Remove '#' character in URLs */
        //$locationProvider.html5Mode(true);

    });
