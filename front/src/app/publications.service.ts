import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PublicationsService {

  public host:string="http://localhost:8082";
  public hostevents:string="http://localhost:8084";

  constructor(private http:HttpClient) { }

  getAllCategories(){
    return this.http.get(this.host+"/categories");
  }

  getAllPublications(){
    return this.http.get(this.host+"/publications");
  }
  getAllEvents(){
    return this.http.get(this.hostevents+"/events");
  }
  saveEvent(p:Event){
    return this.http.post(this.hostevents+"/events/add/",p);
}
deleteEvent(e:any){
  return this.http.delete<void>(this.hostevents+"/events/delete/"+e.id);
}
getEvent(id:any):Observable<Event>{
  return this.http.get<Event>(this.hostevents+"/events/"+id);
}
updateEvent(p:any){
  return this.http.put<Event>(this.hostevents+"/events/update"+p.id,p);
}
participer(idevent:any,id:any){
  return this.http.get("http://localhost:8087/enseignants/event/"+idevent+"/"+id);
}
  
}
