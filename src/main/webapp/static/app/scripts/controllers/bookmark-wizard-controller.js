'use strict';

angular.module('jrocketApp')
  .controller('BookmarkWizardController', ['$scope', '$rootScope', '$modalInstance', 'BookmarkService', 'bookmarkId',
    function ($scope, $rootScope, $modalInstance, bookmarkService, bookmarkId) {

    // Initialize the bookmark object that will be used for wizard (ADDING/EDITION)
    $scope.bookmark = {};

    if (bookmarkId) {
      $scope.bookmark = bookmarkService.get({id: bookmarkId});
    }

    $scope.saveBookmark = function () {
      // Retrieve bookmark
      var bookmark = $scope.bookmark;
      // Server call
      if (bookmark.id) {
        // Merge
        console.log('Updating bookmark...');
        bookmarkService.update({id: bookmark.id}, bookmark,
          function() {
            console.log('Bookmark updated');
            emitRefreshBookmarkListEvent();
          },
          function() {
            console.error('An error occurred during updating');
          });
      } else {
        // Persist
        console.log('Saving bookmark...');
        bookmarkService.save(bookmark,
          function() {
            console.log('Bookmark saved');
            emitRefreshBookmarkListEvent();
          },
          function() {
            console.error('An error occurred during saving');
          });
      }

      // Close the wizard
      $modalInstance.close();
    };

    $scope.cancel = function() {
      $modalInstance.dismiss('cancel');
    };

    function emitRefreshBookmarkListEvent() {
      $rootScope.$emit('refreshBookmarkList');
    }

  }]);
