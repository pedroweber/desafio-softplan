
import { Component, Input, OnInit, Injectable } from '@angular/core';

import { NgbModal, NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { PessoaServiceService } from '../../../api/pessoa-service.service';

import { Validacoes } from '../../../validacoes';

@Injectable({
  providedIn: "root"
})
@Component({
  selector: 'app-create-pessoa',
  templateUrl: './create-pessoa.component.html',
  styleUrls: ['./create-pessoa.component.scss']
})
export class CreatePessoaComponent implements OnInit {

  @Input() title = `Information`;

  pessoa: any;

  public pessoaForm: FormGroup;

  constructor(
    public activeModal: NgbActiveModal, private service: PessoaServiceService
  ) { }

  ngOnInit() {
    this.pessoa = {};

    this.pessoaForm = new FormGroup({
      'nome': new FormControl(this.pessoa.nome, [Validators.required]),
      'sexo': new FormControl(this.pessoa.sexo),
      'email': new FormControl(this.pessoa.email, [Validators.email]),
      'dataNascimento': new FormControl(this.pessoa.dataNascimento),
      'naturalidade': new FormControl(this.pessoa.naturalidade),
      'nacionalidade': new FormControl(this.pessoa.nacionalidade),
      'cpf': new FormControl(this.pessoa.cpf, Validators.compose([Validators.required, Validacoes.ValidaCpf]))
    });
  }

  criar() {
    console.log(this.pessoaForm.value);
    this.service.criar(this.pessoaForm.value).subscribe(resposta => {
      this.pessoaForm.reset();
    });
    this.activeModal.close();
  }
  close() {
    this.activeModal.close();
  }
}
