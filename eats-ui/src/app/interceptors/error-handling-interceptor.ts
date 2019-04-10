import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';

import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

import { ToastrService } from 'ngx-toastr';
import { Injectable } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';

@Injectable()
export class ErrorHandlingInterceptor implements HttpInterceptor {

  constructor(private toasterService: ToastrService,
              private authenticationService: AuthenticationService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    return next.handle(req)
      .pipe(
        //retry(1),
        catchError(err => {
          let mensagemDeErro = '';
          if (err.error instanceof ErrorEvent) {
            mensagemDeErro = err.error.message;
          } else {
            if (err.status && err.message) {
              mensagemDeErro = `[${err.status}] ${err.message}`;
            }
          }
          this.toasterService.error(mensagemDeErro, 'Erro no servidor');

          return throwError(err);
        })
      );
  }

}
