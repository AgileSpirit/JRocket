'use strict';

angular.module('jrocketApp')
  .controller('HeaderController', ['$scope', '$modal', function ($scope, $modal) {

      $scope.addBookmark = function() {
        $modal.open({
          templateUrl: 'views/bookmark-wizard.html',
          controller: 'BookmarkWizardController',
          resolve: {
            bookmarkId: function(){
              return null;
            }
          }
        });
      };

    }]);
