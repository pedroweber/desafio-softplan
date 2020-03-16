import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Basic ' + btoa('softplayer:softplayer')
  })
};

@Injectable({
  providedIn: 'root'
})
export class PessoaServiceService {

  apiRestUrl = 'http://localhost:8080/pessoas/';

  

  constructor(private http: HttpClient) { }

  listar() {
    return this.http.get<Array<any>>(this.apiRestUrl, httpOptions);
  }

  criar(pessoa: any) {
    return this.http.post(this.apiRestUrl + "v1", pessoa, httpOptions);
  }

  editar(pessoa: any) {
    return this.http.put(this.apiRestUrl + "v1/" + pessoa.cpf, pessoa, httpOptions);
  }

  deletar(pessoa: any) {
    return this.http.delete(this.apiRestUrl + pessoa.cpf, httpOptions);
  }

}
