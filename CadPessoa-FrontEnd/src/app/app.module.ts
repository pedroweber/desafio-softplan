import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainPageComponent } from './pages/main-page/main-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CreatePessoaComponent } from './pages/modal/create-pessoa/create-pessoa.component';
import { InfoPessoaComponent } from './pages/modal/info-pessoa/info-pessoa.component';
import { NgbModule, NgbActiveModal, NgbModalModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {PessoaServiceService} from './api/pessoa-service.service';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { HttpClientModule } from '@angular/common/http';
import { SourcePageComponent } from './source-page/source-page.component';
import { NgxMaskModule } from 'ngx-mask';
import { DataTablesModule } from 'angular-datatables';

@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
    CreatePessoaComponent,
    InfoPessoaComponent,
    SourcePageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FontAwesomeModule,
    FormsModule,
    NgbModalModule,
    DragDropModule,
    HttpClientModule,
    NgxMaskModule.forRoot(),
    ReactiveFormsModule,
    DataTablesModule
  ],
  providers: [NgbActiveModal, PessoaServiceService],
  bootstrap: [AppComponent],
  entryComponents: [
    CreatePessoaComponent,
    InfoPessoaComponent
  ]
})
export class AppModule { }
