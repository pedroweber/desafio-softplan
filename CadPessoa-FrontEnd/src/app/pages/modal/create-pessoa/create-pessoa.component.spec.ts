import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreatePessoaComponent } from './create-pessoa.component';

describe('CreateTaskComponent', () => {
  let component: CreatePessoaComponent;
  let fixture: ComponentFixture<CreatePessoaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CreatePessoaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreatePessoaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
