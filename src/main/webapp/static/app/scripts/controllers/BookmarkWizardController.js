'use strict';

angular.module('jrocketApp')
  .controller('BookmarkWizardController', ['$scope', '$rootScope', 'BookmarkService', function ($scope, $rootScope, bookmarkService) {

    // Initialize the bookmark object that will be used for wizard (ADDING/EDITION)
    $scope.bookmark = {};

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
          });
      } else {
        // Persist
        console.log('Saving bookmark...');
        bookmarkService.save(bookmark,
          function() {
            console.log('Bookmark saved');
            emitRefreshBookmarkListEvent();
          });
      }

      // Close the wizard
      closeBookmarkWizard();
    };

    function emitRefreshBookmarkListEvent() {
      $rootScope.$emit('refreshBookmarkList');
    }

    $rootScope.$on('openBookmarkWizardEvent', function(event, args){
      openBookmarkWizard(args);
    });

    function openBookmarkWizard(bookmarkId) {
      $scope.bookmark = {};
      if (typeof bookmarkId != 'undefined') {
        $scope.bookmark = bookmarkService.get({id: bookmarkId});
      }
      $('#bookmarkWizard').modal('show');
    }

    function closeBookmarkWizard() {
      $('#bookmarkWizard').modal('hide');
    }

  }]);
