import { ApplicationConfig, importProvidersFrom } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideHttpClient, withInterceptors, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MsalModule, MsalInterceptor, MsalGuard, MsalBroadcastService } from '@azure/msal-angular';
import { PublicClientApplication, BrowserCacheLocation, LogLevel } from '@azure/msal-browser';
import { msalConfig, msalInterceptorConfig } from './auth.config';

const msalInstance = new PublicClientApplication(msalConfig);

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),
    provideHttpClient(
      withInterceptors([])
    ),
    importProvidersFrom(
      MsalModule.forRoot(
        msalInstance,
        msalInterceptorConfig,
        null
      )
    ),
    MsalGuard,
    MsalBroadcastService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: MsalInterceptor,
      multi: true
    }
  ]
};
