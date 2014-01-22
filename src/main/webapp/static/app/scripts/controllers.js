'use strict';

/* Controllers */

angular.module('jwsControllers', [])
    .controller('BookmarkListCtrl', ['$scope', 'bookmarkService', function ($scope, bookmarkService) {
        // Instantiate an object to store your scope data in (Best Practices)
        $scope.data = {};
        // Initialize the bookmark object that will be used for wizard (ADDING/EDITION)
        $scope.data.bookmark = {};

        bookmarkService.query(function (response) {
            // Assign the response INSIDE the callback
            $scope.data.bookmarks = response;
        });

        $scope.getBookmark = function (id) {
            $scope.data.bookmark = bookmarkService.get({id: id});
            console.log("bookmark.title = " + $scope.data.bookmark);
        };

        $scope.removeBookmark = function (bookmark) {
            console.log("Removing bookmark...");
            console.log("id=" + bookmark.id);
            // Server call
            bookmarkService.remove({id: bookmark.id});
            // Client update
            $scope.data.bookmarks.splice($scope.data.bookmarks.indexOf(bookmark), 1);
        };

        $scope.saveBookmark = function () {
            console.log("Saving bookmark...");
            // Retrieve bookmark
            var savingBookmark = angular.copy($scope.data.bookmark);
            // Server call
            var savedBookmark = bookmarkService.save({id: savingBookmark.id}, savingBookmark,
                function () {
                    console.log("It worked ! :-)");
                },
                function () {
                    console.log("It failed... :-(");
                }
            );
            // Client update
            console.log("Bookmark saved, id=" + savedBookmark.id);
            this.closeBookmarkWizard();
        }

        $scope.editBookmark = function (id) {
            console.log("Open wizard...");
            bookmarkService.get({id: id}, function (data) {
                angular.extend($scope.data.bookmark, data);
                $('#bookmarkWizard').foundation('reveal', 'open');
                console.log("Wizard opened...");
            });
        }

        $scope.closeBookmarkWizard = function () {
            console.log("Closing wizard...");
            $scope.data.bookmark = {};
            bookmarkService.query(function (response) {
                // Assign the response INSIDE the callback
                $scope.data.bookmarks = response;
                $('#bookmarkWizard').foundation('reveal', 'close');
                console.log("Wizard closed...");
            });
        }

    }])

    .controller('BookmarkDetailCtrl', ['$scope', '$routeParams', function ($scope, $routeParams) {
        $scope.bookmarkId = $routeParams.bookmarkId;
    }]);