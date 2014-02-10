'use strict';

angular.module('jrocketApp')
  .controller('BookmarkWizardController', ['$scope', '$rootScope', 'BookmarkService', function ($scope, $rootScope, bookmarkService) {

//    $rootScope.on('wizard.open', my_open_function);
//    $rootScope.on('$destroy', do_unbind);

    $scope.wizard = {};

    // Initialize the bookmark object that will be used for wizard (ADDING/EDITION)
    $scope.wizard.bookmark = {};

    $scope.saveBookmark = function () {
      // Retrieve bookmark
      var bookmark = $scope.wizard.bookmark;
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
      $scope.wizard.bookmark = {};
      if (typeof bookmarkId != 'undefined') {
        $scope.wizard.bookmark = bookmarkService.get({id: id});;
      }
      $('#bookmarkWizard').modal('show');
    };

    $scope.closeBookmarkWizard = function () {
      $('#bookmarkWizard').modal('hide');
    };

  }]);
