import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthenticatedGuardService } from './helpers/authenticated-guard.service';
import { RedirectService } from './helpers/redirect.service';
import { SessionManagerService } from './helpers/session-manager.service';

import { HomeComponent } from './home/home.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    {
        path: 'home',
        component: HomeComponent,
        canActivate: [AuthenticatedGuardService],
        children: [
            { path: '', component: DashboardComponent }
        ]
    },
    { path: '**', component: NotFoundComponent }
]

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }

export const RoutedComponents = [
    HomeComponent,
    NotFoundComponent,
    DashboardComponent,
    LoginComponent
];

export const RoutedProviders = [
    RedirectService,
    SessionManagerService,
    AuthenticatedGuardService
];