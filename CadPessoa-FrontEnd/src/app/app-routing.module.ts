import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SourcePageComponent } from './source-page/source-page.component';
import { AppComponent } from './app.component';
import { MainPageComponent } from './pages/main-page/main-page.component';


const routes: Routes = [
  { path: '', component: MainPageComponent },
  { path: 'source', component: SourcePageComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
