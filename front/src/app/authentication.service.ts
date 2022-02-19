import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  host2:string="http://localhost:8087";
  jwt!:any;
  public currentUsername!:any;
  public roles!:any;
  public currentEnseignant!:any;

  constructor(private http:HttpClient) { }

  login(data:any){
    return this.http.post(this.host2+"/login",data,{observe:'response'});
  }

  saveToken(jwt:string){
    localStorage.setItem('token',jwt);
    this.jwt=jwt;
    this.parseJWT();
  }

  parseJWT(){
    let jwtHelper =new JwtHelperService();
    let objJWT=jwtHelper.decodeToken(this.jwt);
    this.currentUsername=objJWT.sub;
    console.log(this.currentUsername);
    this.roles=objJWT.roles;
    console.log(this.roles);
  }
  getUser(){
    return this.http.get("http://localhost:8087/appUsers/search/getUser?username="+this.currentUsername);
  }
  

  isAdmin(){
    return this.roles.indexOf('ADMIN')>=0;
  }
  isUser(){
    return this.roles.indexOf('USER')>=0;
  }
  isAuthenticated(){
    return this.roles && (this.isAdmin() || this.isUser());
  }
  loadToken(){
    this.jwt=localStorage.getItem('token');
    this.parseJWT();
  }
  logout(){
    localStorage.removeItem('token');
    this.initParams();
  }
  initParams(){
    this.jwt=undefined;
    this.currentUsername=undefined;
    this.roles=undefined;
  }
}
