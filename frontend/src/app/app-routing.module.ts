import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent }   from './login/login.component';
import { RegisterComponent }   from './register/register.component';
import { PortfolioComponent }   from './portfolio/portfolio.component';
import { BondinfoComponent }   from './bondinfo/bondinfo.component';
import { SellbondComponent }   from './sellbond/sellbond.component';
import { AllbondsComponent } from './allbonds/allbonds.component';
import { BuybondComponent } from './buybond/buybond.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'portfolio', component: PortfolioComponent },
  { path: 'bondinfo', component: BondinfoComponent },
  { path: 'bondinfo/:id', component: BondinfoComponent },
  { path: 'sellbond/:id', component: SellbondComponent },
  { path: 'allbonds', component: AllbondsComponent },
  { path: 'buybond/:id', component: BuybondComponent }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
