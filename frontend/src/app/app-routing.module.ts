import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent }   from './login/login.component';
import { RegisterComponent }   from './register/register.component';
import { PortfolioComponent }   from './portfolio/portfolio.component';
import { BondinfoComponent }   from './bondinfo/bondinfo.component';
import { SellbondComponent }   from './sellbond/sellbond.component';
import { AllbondsComponent } from './allbonds/allbonds.component';
import { BuybondComponent } from './buybond/buybond.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  { path: '', redirectTo: '/portfolio', canActivate: [AuthGuard], pathMatch: 'full',},
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'portfolio', component: PortfolioComponent, canActivate: [AuthGuard] },
  { path: 'bondinfo', component: BondinfoComponent, canActivate: [AuthGuard] },
  { path: 'bondinfo/:id', component: BondinfoComponent, canActivate: [AuthGuard] },
  { path: 'sellbond/:id', component: SellbondComponent, canActivate: [AuthGuard] },
  { path: 'allbonds', component: AllbondsComponent, canActivate: [AuthGuard] },
  { path: 'buybond/:id', component: BuybondComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '' }

];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
