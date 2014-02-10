'use strict';

angular.module('jrocketApp')
  .controller('BookmarkWizardController', ['$scope', 'BookmarkService', function ($scope, bookmarkService) {

    // Instantiate an object to store your scope data in (Best Practices)
    $scope.data = {};

    // Initialize the bookmark object that will be used for wizard (ADDING/EDITION)
    $scope.data.bookmark = {};

    $scope.saveBookmark = function () {
      // Retrieve bookmark
      var bookmark = $scope.data.bookmark;
      // Server call
      if (bookmark.id) {
        // Merge
        console.log('Updating bookmark...');
        bookmarkService.update({id: bookmark.id}, bookmark,
          function () {
            console.log('Bookmark updated');
          },
          function () {
            console.log('Bookmark update KO');
          });
      } else {
        // Persist
        console.log('Saving bookmark...');
        bookmarkService.save(bookmark);
        console.log('Bookmark saved');
      }
      // Client update
      this.closeBookmarkWizard();
    };

    $scope.openBookmarkWizard = function (bookmarkId) {
      $scope.data.bookmark = {};
      if (typeof bookmarkId != 'undefined') {
        $scope.data.bookmark = bookmarkService.get({id: id});;
      }
      $('#bookmarkWizard').modal('show');
    };

    $scope.closeBookmarkWizard = function () {
      $('#bookmarkWizard').modal('hide');
    };

  }]);
