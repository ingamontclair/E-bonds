import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule }    from '@angular/forms';
// Import the Http Module and our Data Service
import { HttpModule } from '@angular/http';
import { DataService } from './data.service';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { AppRoutingModule } from './app-routing.module';
import { RegisterComponent } from './register/register.component';
import { PortfolioComponent } from './portfolio/portfolio.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { BondinfoComponent } from './bondinfo/bondinfo.component';
import { SellbondComponent } from './sellbond/sellbond.component';
import { AllbondsComponent } from './allbonds/allbonds.component';
import { BuybondComponent } from './buybond/buybond.component';
import { AuthenticationService } from './authentication.service';
import { AuthGuard } from './auth.guard';
import { HistoryComponent } from './history/history.component';
import { AddmoneyComponent } from './addmoney/addmoney.component';
import { WithdrawComponent } from './withdraw/withdraw.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    PortfolioComponent,
    HeaderComponent,
    FooterComponent,
    BondinfoComponent,
    SellbondComponent,
    AllbondsComponent,
    BuybondComponent,
    HistoryComponent,
    AddmoneyComponent,
    WithdrawComponent
  ],
  imports: [
    BrowserModule,
    NgbModule.forRoot(),
    HttpModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [DataService, HeaderComponent, AuthenticationService, AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
