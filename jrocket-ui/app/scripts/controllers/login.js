'use strict';

/**
 * @ngdoc function
 * @name jrocketUiApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the jrocketUiApp
 */
angular.module('jrocketUiApp')
  .controller('LoginCtrl', ['$scope', '$location', function ($scope, $location) {
        $scope.connect = function() {
            if ($scope.login === 'admin' && $scope.password === '1') {
                $location.path('/bookmarks');
            } else {
                $location.path('/error');
            }
        };
  }]);
