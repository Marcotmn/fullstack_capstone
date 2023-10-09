import { Component, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Route, RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { FormGroup, FormBuilder, Validator } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './home/home/home.component';
import { AnnunciComponent } from './components/annunci/annunci.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';

import { UserComponent } from './components/users/users.component';
import { AuthGuard } from './auth/auth.guard';
import { TokenInterceptor } from './auth/token.interceptor';
import { PaginaRegistrazioneComponent } from './components/pagina-registrazione/pagina-registrazione.component';
import { FaqComponent } from './components/faq/faq.component';
import { CreaAnnunciComponent } from './components/crea-annunci/crea-annunci.component';
import { CategoriaConversionPipe } from './components/annunci/categoria-conversion.pipe';
import { CommonModule } from '@angular/common';
import { AnnunciDetailsComponent } from './components/annunci-details/annunci-details.component';
import { CreaProposteComponent } from './components/crea-proposte/crea-proposte.component';
import { ProposteDetailsComponent } from './components/proposte-details/proposte-details.component';

const routes: Route[] = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'home',
    component: HomeComponent,
  },
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'faq',
    component: FaqComponent,
  },
  { path: 'annunci/nuovo-annuncio', component: CreaAnnunciComponent },

  {
    path: 'registrazione',
    component: RegisterComponent,
  },

  {
    path: 'annunci/bacheca-annunci',
    component: AnnunciComponent,
    canActivate: [AuthGuard],
  },
  { path: 'annunci/:annuncioId', component: AnnunciDetailsComponent },

  {
    path: 'crea-proposta/:annuncioId',
    component: CreaProposteComponent,
  },

  { path: 'proposte-details/:id', component: ProposteDetailsComponent },

  {
    path: 'users',
    component: UserComponent,
    canActivate: [AuthGuard],
  },
];

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    CreaProposteComponent,
    AnnunciComponent,
    UserComponent,
    PaginaRegistrazioneComponent,
    LoginComponent,
    FaqComponent,
    CreaAnnunciComponent,
    CategoriaConversionPipe,
    RegisterComponent,
    AnnunciDetailsComponent,
    CreaProposteComponent,
    ProposteDetailsComponent,
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes),
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: TokenInterceptor, multi: true },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
