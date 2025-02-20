using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using Microsoft.EntityFrameworkCore;

namespace E_mart.Models;

[Table("cartitems")]
[Index("Cartid", Name = "FK5vi0udj5ia7o0h6k6r0op1do6")]
[Index("Productid", Name = "FKp74gufw7pdc246iolcggngpp")]
public partial class Cartitem
{
    [Key]
    [Column("cartitemid")]
    public int Cartitemid { get; set; }

    [Column("added_at")]
    [MaxLength(6)]
    public DateTime? AddedAt { get; set; }

    [Column("quantity")]
    public int Quantity { get; set; }

    [Column("cartid")]
    public int Cartid { get; set; }

    [Column("productid")]
    public int Productid { get; set; }

    [ForeignKey("Cartid")]
    [InverseProperty("Cartitems")]
    public virtual Cart Cart { get; set; } = null!;

    [ForeignKey("Productid")]
    [InverseProperty("Cartitems")]
    public virtual Product Product { get; set; } = null!;
}
