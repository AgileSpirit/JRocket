'use strict';

/**
 * @ngdoc function
 * @name jrocketUiApp.controller:BookmarksCtrl
 * @description
 * # BookmarksCtrl
 * Controller of the jrocketUiApp
 */
angular.module('jrocketUiApp')
    .controller('BookmarksCtrl', ['$scope', 'BookmarkService', function ($scope, bookmarkService) {

        // Model data
        $scope.query = '';
        $scope.currentPage = 1;
        $scope.pageSize = 7;
        $scope.bookmarks = {};
        $scope.lastPage = -1;
        $scope.pageLinks = {};

        // Initialize
        function loadBookmarks(query, page, size) {
            var offset = pageToOffset(page, $scope.pageSize);
            bookmarkService.search({'query' : query, 'offset': offset , 'size': size}, function(response) {
                $scope.bookmarks = response.bookmarks;
                $scope.totalItems = response.totalItems;
                $scope.currentPage = offsetToPage(response.offset, $scope.pageSize);
                $scope.lastPage = Math.round($scope.totalItems / size );
            });
        }

        // o = ( p - 1 ) * s
        function pageToOffset(pageIndex, pageSize) {
            return (pageIndex - 1) * pageSize;
        }

        // p = ( o / s ) + 1
        function offsetToPage(offset, pageSize) {
            return (offset / pageSize) + 1;
        }

        $scope.previousPageDisabled = function() {
          return $scope.currentPage === 1;
        };

        $scope.nextPageDisabled = function() {
            return $scope.currentPage === $scope.lastPage;
        };

        $scope.getPages = function() {
            if ($scope.lastPage > 0) {
                return new Array($scope.lastPage);
            }
            return 'undefined';
        };

        $scope.changePage = function (page) {
            loadBookmarks($scope.query, page, $scope.pageSize);
        };

        $scope.previousPage = function() {
            if ($scope.currentPage > 1) {
                $scope.changePage($scope.currentPage - 1);
            }
        };

        $scope.nextPage = function() {
            if ($scope.currentPage < $scope.lastPage) {
                $scope.changePage($scope.currentPage + 1);
            }
        };

        function initialize() {
            loadBookmarks($scope.query, $scope.currentPage, $scope.pageSize);
        }
        initialize();

    }]);
