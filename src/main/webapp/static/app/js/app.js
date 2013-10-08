'use strict';


// Declare app level module which depends on filters, and services
angular.module('impulse', ['impulse.filters', 'impulse.services', 'impulse.directives', 'impulse.controllers']).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.when('/bookmarks', {templateUrl: 'partials/bookmark-list.html', controller: 'BookmarkListCtrl'});
    $routeProvider.when('/bookmarks/:bookmarkId', {templateUrl: 'partials/bookmark-detail.html', controller: 'BookmarkDetailCtrl'});
    $routeProvider.otherwise({redirectTo: '/bookmarks'});
  }]);