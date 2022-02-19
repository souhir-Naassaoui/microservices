import { Router } from '@angular/router';
import { AuthenticationService } from './../authentication.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  jwt:any;
  username:any;
  constructor(private authService:AuthenticationService, private route:Router) { }

  ngOnInit(): void {
  }
  
  onLogin(data:any){
    this.authService.login(data).subscribe(resp=>{
      this.jwt=resp.headers.get('Authorization');
      this.authService.saveToken(this.jwt);

      //console.log(this.authService.currentUsername);
      this.route.navigateByUrl('/');
    },err=>{
      console.log(err);
    })
  }

  
}
