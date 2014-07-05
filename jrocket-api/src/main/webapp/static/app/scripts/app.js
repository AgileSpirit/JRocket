'use strict';

angular.module('jrocketApp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute',
    'ui.bootstrap'
  ])
  .config(function ($routeProvider, $httpProvider) {
    $routeProvider.when('/bookmarks', {templateUrl: 'views/bookmark-list.html'});
    $routeProvider.when('/test', {templateUrl: 'views/main.html', controller: 'MainCtrl'});
    $routeProvider.otherwise({redirectTo: '/bookmarks'});

    /* CORS... */
    /* http://stackoverflow.com/questions/17289195/angularjs-post-data-to-external-rest-api */
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];

  });


