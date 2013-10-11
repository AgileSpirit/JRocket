'use strict';


// Declare app level module which depends on filters, and services
var jwsApp = angular.module('jws', ['jws.filters', 'jws.services', 'jws.directives', 'jws.controllers'])
  .config(['$routeProvider', '$httpProvider',function($routeProvider, httpProvider) {
    $routeProvider.when('/bookmarks', {templateUrl: 'partials/bookmark-list.html', controller: 'BookmarkListCtrl'});
    $routeProvider.when('/bookmarks/:bookmarkId', {templateUrl: 'partials/bookmark-detail.html', controller: 'BookmarkDetailCtrl'});
    $routeProvider.otherwise({redirectTo: '/bookmarks'});

    // Add REST CORS support
    httpProvider.defaults.useXDomain = true;
    delete httpProvider.defaults.headers.common['X-Requested-With'];
  }
]);
