import { BrowserModule } from '@angular/platform-browser';
import { LOCALE_ID, NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';

import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/pt';

import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { ToastrModule } from 'ngx-toastr';

import { AppComponent } from './app.component';

import { AdminModule } from './admin/admin.module';
import { PrincipalModule } from './principal/principal.module';
import { RestaurantesModule } from './restaurantes/restaurantes.module';

import { ErrorHandlingInterceptor } from './interceptors/error-handling-interceptor';


registerLocaleData(localeFr, 'pt');

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule,
    RouterModule.forRoot([]),
    NgbModule,
    ToastrModule.forRoot(),
    AdminModule,
    PrincipalModule,
    RestaurantesModule
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'pt' },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorHandlingInterceptor , multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
