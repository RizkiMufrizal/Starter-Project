import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { Ng2Webstorage } from 'ngx-webstorage';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { AppRoutingModule, RoutedComponents, RoutedProviders } from './app.routing.module';

@NgModule({
  declarations: [
    AppComponent,
    RoutedComponents
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    AppRoutingModule,
    Ng2Webstorage,
    NgbModule.forRoot()
  ],
  providers: [RoutedProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
