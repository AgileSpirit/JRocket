'use strict';

angular.module('jrocketApp.bookmarks.controller', [])
  .controller('BookmarksController', ['$scope', 'BookmarksService', function ($scope, bookmarksService) {
    // Instantiate an object to store your scope data in (Best Practices)
    $scope.data = {};
    // Initialize the bookmark object that will be used for wizard (ADDING/EDITION)
    $scope.data.bookmark = {};

    $scope.data.bookmarks = bookmarksService.query();

    $scope.getBookmark = function (id) {
      $scope.data.bookmark = bookmarkService.get({id: id});
    };

    $scope.removeBookmark = function (bookmark) {
      // Server call
      bookmarkService.remove({id: bookmark.id});
      // Client update
      $scope.data.bookmarks = bookmarksService.query();
    };

    $scope.saveBookmark = function () {
      // Retrieve bookmark
      var bookmark = $scope.data.bookmark;
      // Server call
      if (bookmark.id) {
        // Merge
        console.log("Updating bookmark...");
        bookmarkService.update({id: bookmark.id}, bookmark,
          function () {
            console.log("Bookmark updated");
          },
          function () {
            console.log("Bookmark update KO");
          });
      } else {
        // Persist
        console.log("Saving bookmark...");
        bookmarkService.save(bookmark);
        console.log("Bookmark saved");
      }
      // Client update
      this.closeBookmarkWizard();
    };

    $scope.editBookmark = function (id) {
      bookmarkService.get({id: id}, function (data) {
        angular.extend($scope.data.bookmark, data);
        $('#bookmarkWizard').foundation('reveal', 'open');
      });
    };

    $scope.closeBookmarkWizard = function () {
      $scope.data.bookmark = {};
      bookmarkService.query(function (response) {
        // Assign the response INSIDE the callback
        $scope.data.bookmarks = response;
        $('#bookmarkWizard').foundation('reveal', 'close');
      });
    };

  }]);
