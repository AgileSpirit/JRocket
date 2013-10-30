'use strict';

// Declare app level module which depends on filters, and services
angular.module('jwsApp', ['jws.services', 'jws.controllers'])
    .config(['$routeProvider', '$httpProvider',function($routeProvider, httpProvider) {
        $routeProvider.when('/bookmarks', {templateUrl: 'views/bookmark-list.html', controller: 'BookmarkListCtrl'});
        $routeProvider.when('/bookmarks/:bookmarkId', {templateUrl: 'views/bookmark-detail.html', controller: 'BookmarkDetailCtrl'});
        $routeProvider.otherwise({redirectTo: '/bookmarks'});

        // Add REST CORS support
        httpProvider.defaults.useXDomain = true;
        delete httpProvider.defaults.headers.common['X-Requested-With'];
    }
    ])
    .run(function($rootScope) {
        $rootScope.$on('$viewContentLoaded', function () {
            $(document).foundation();
        });
    });
