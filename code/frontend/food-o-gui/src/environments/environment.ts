// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiUrlHostAndPort: "http://localhost:9090",
  itemServiceUrlPrefix: "/food-o-meter-item-service/v1/food-items",
  orderServiceUrlPrefix: "/food-o-meter-order-service/v1",
  userServiceUrlPrefix: "/food-o-meter-user-service/v1/users",

  foodItemCategory: ['STARTER', 'MAINS', 'DESSERT', 'BEVERAGES'],
  staffRoles: ['ADMIN', 'DELIVERY_AGENT', 'CHEF'],
  customerRole: 'CUSTOMER',
  adminRole: 'ADMIN',
  chefRole: 'CHEF',
  deliveryAgentRole: 'DELIVERY_AGENT',

  sessionUser: 'loggedInUser'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
