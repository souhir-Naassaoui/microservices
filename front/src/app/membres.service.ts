import { Enseignant } from './model/Enseignant.model';

import { Etudiant } from './model/Etudiant.model';
import { Member } from './model/Member.model';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { EditUserComponent } from './edit-user/edit-user.component';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class MembresService {

  public memberHost:string="http://localhost:8087";
  public pubHost:string="http://localhost:8082";


  constructor(private http:HttpClient) { }

  getAllStudents(){
    return this.http.get(this.memberHost+"/etudiants");
  }
  getAllTeachers(){
    return this.http.get(this.memberHost+"/enseignants");
  }
  saveEtudiant(member:Member):Observable<Member>{
    
    return this.http.post<Member>(this.memberHost+"/registerEtud",member);
  }
  saveEnseignant(member:Member):Observable<Member>{
    
    return this.http.post<Member>(this.memberHost+"/registerEns",member);
  }
  deleteMember(id:any):Observable<void>{
    return this.http.delete<void>(this.memberHost+"/etudiants/"+id);
  }
  updateEtudiant(etud:Etudiant,encad:any):Observable<Etudiant>{
    console.log(etud);
    let url=this.http.put<Etudiant>(this.memberHost+"/etudiants/"+etud.id+"?username="+encad,etud);
    console.log(url);
    return url;
  }
  updateEnseignant(etud:Enseignant):Observable<Enseignant>{
    console.log(etud);
    return this.http.put<Enseignant>(this.memberHost+"/enseignants/"+etud.id,etud);
  }
  addRole(username:string){
    return this.http.get<void>(this.memberHost+"/enseignants/addRole?username="+username);
  }
  uploadPhotoProduct(file:File,idProduct:any,type:any): Observable<HttpEvent<{}>>{
    let formdata: FormData = new FormData();
    formdata.append('file',file);
    var req:any;
    if(type=="ens"){
      req = new HttpRequest('POST', this.memberHost+'/enseignants/uploadPhoto/'+idProduct,formdata,{
        reportProgress:true,
        responseType:'text'
      });
    }else{
      req = new HttpRequest('POST', this.memberHost+'/etudiants/uploadPhoto/'+idProduct,formdata,{
        reportProgress:true,
        responseType:'text'
      });
    }
    return this.http.request(req);
  }
  getEnseignant(id:any){
    return this.http.get(this.memberHost+"/enseignants/"+id);
  }
  getEtudiant(id:any){
    return this.http.get(this.memberHost+"/etudiants/"+id);
  }
  uploadEnseignant(file: File,id:any): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', this.memberHost+'/enseignants/uploadCV/'+id+'/files', formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }
  uploadPublication(file: File,id:any,title:any): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', this.pubHost+'/publications/upload/'+id+'/files?title='+title, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }
  uploadEtudiant(file: File,id:any): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', this.memberHost+'/etudiants/uploadCV/'+id+'/files', formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }
  getPublications(id:any){
    return this.http.get("http://localhost:8082/publications/get/"+id);
  }
  getPublication(id:any){
    return this.http.get("http://localhost:8082/publications/"+id);
  }
}
